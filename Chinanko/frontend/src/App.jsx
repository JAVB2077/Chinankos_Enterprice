import React, { useState } from 'react'

const DEFAULT_BASE = 'http://localhost:8080'

function App() {
  const [base, setBase] = useState(DEFAULT_BASE)
  const [path, setPath] = useState('/api')
  const [method, setMethod] = useState('GET')
  const [body, setBody] = useState('')
  const [headersText, setHeadersText] = useState('Content-Type: application/json')
  const [response, setResponse] = useState(null)
  const [loading, setLoading] = useState(false)

  const send = async () => {
    setLoading(true)
    setResponse(null)
    try {
      const url = base.replace(/\/$/, '') + '/' + path.replace(/^\//, '')
      const headers = {}
      headersText.split('\n').forEach(line => {
        const [k, ...rest] = line.split(':')
        if (!k) return
        headers[k.trim()] = rest.join(':').trim()
      })

      const opts = { method, headers }
      if (method !== 'GET' && method !== 'HEAD' && body) {
        try {
          opts.body = JSON.stringify(JSON.parse(body))
        } catch (e) {
          // send as raw if not valid json
          opts.body = body
        }
      }

  const res = await fetch(url, opts)
  const text = await res.text()
  let parsed = text
  try { parsed = JSON.parse(text) } catch (e) {}
  // Convert Headers to plain object
  const headersObj = {}
  res.headers.forEach((value, key) => { headersObj[key] = value })
  setResponse({ status: res.status, ok: res.ok, headers: headersObj, body: parsed })
    } catch (err) {
      setResponse({ error: String(err) })
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container">
      <h1>Chinanko Frontend — API Explorer</h1>

      <div className="row">
        <label>Backend base URL</label>
        <input value={base} onChange={e => setBase(e.target.value)} />
      </div>

      <div className="row">
        <label>Path</label>
        <input value={path} onChange={e => setPath(e.target.value)} />
        <select value={method} onChange={e => setMethod(e.target.value)}>
          <option>GET</option>
          <option>POST</option>
          <option>PUT</option>
          <option>PATCH</option>
          <option>DELETE</option>
        </select>
      </div>

      <div className="row">
        <label>Headers (one per line, Name: value)</label>
        <textarea value={headersText} onChange={e => setHeadersText(e.target.value)} rows={3} />
      </div>

      <div className="row">
        <label>Body (JSON)</label>
        <textarea value={body} onChange={e => setBody(e.target.value)} rows={8} />
      </div>

      <div className="row actions">
        <button onClick={send} disabled={loading}>{loading ? 'Enviando...' : 'Enviar'}</button>
        <button onClick={() => { setBase(DEFAULT_BASE); setPath('/api'); setMethod('GET'); setBody(''); setHeadersText('Content-Type: application/json'); setResponse(null) }}>Reset</button>
      </div>

      <div className="row">
        <h2>Response</h2>
        {response ? (
          response.error ? (
            <pre className="error">{response.error}</pre>
          ) : (
            <div>
              <div>Status: {response.status} {response.ok ? 'OK' : ''}</div>
              <h3>Headers</h3>
              <pre>{JSON.stringify(response.headers, null, 2)}</pre>
              <h3>Body</h3>
              <pre>{typeof response.body === 'string' ? response.body : JSON.stringify(response.body, null, 2)}</pre>
            </div>
          )
        ) : (
          <div>No response yet</div>
        )}
      </div>
    </div>
  )
}

export default App
