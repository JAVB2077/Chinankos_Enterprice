# Chinanko Frontend

This is a minimal React + Vite frontend to interact with the Chinanko backend. It provides a small API explorer where you can type a path, HTTP method, headers and body, then send requests to the backend and inspect responses.

Quick start (Windows PowerShell):

```powershell
cd C:\Users\anton\Downloads\Chinanko\frontend
npm install
npm run dev
```

Open http://localhost:5173 in your browser.

Notes:
- The default backend base URL in the UI is http://localhost:8080 (adjust if your Spring Boot server runs on another port).
- If the backend blocks requests due to CORS, either enable CORS in the Spring Boot app (recommended) or run a proxy.

Suggested endpoints to try (based on backend controllers):
- /api/users
- /api/roles
- /api/states
- /api/towns
- /api/notifications

If you want a richer frontend (pages, CRUD forms, auth), I can scaffold a React app with routes and components for each entity.
