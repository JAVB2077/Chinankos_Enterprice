package chinanko.chinanko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.ReportSuggestedPointRequest;
import chinanko.chinanko.dto.ReportSuggestedPointResponse;
import chinanko.chinanko.mapper.ReportSuggestedPointMapper;
import chinanko.chinanko.model.ReportSuggestedPoint;
import chinanko.chinanko.model.SuggestedPoint;
import chinanko.chinanko.model.TypeOfReportPoint;
import chinanko.chinanko.model.User;
import chinanko.chinanko.repository.ReportSuggestedPointRepository;
import chinanko.chinanko.repository.SuggestedPointRepository;
import chinanko.chinanko.repository.TypeOfReportPointRepository;
import chinanko.chinanko.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportSuggestedPointService {

    private final ReportSuggestedPointRepository repository;
    private final TypeOfReportPointRepository typeOfReportPointRepository;
    private final SuggestedPointRepository suggestedPointRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReportSuggestedPointResponse create(ReportSuggestedPointRequest request) {
        TypeOfReportPoint typeOfReportPoint = typeOfReportPointRepository.findById(request.getIdTypeOfReportPoint())
                .orElseThrow(() -> new RuntimeException("Type of Report Point not found with id: " + request.getIdTypeOfReportPoint()));

        SuggestedPoint suggestedPoint = suggestedPointRepository.findById(request.getIdSuggestedPoint())
                .orElseThrow(() -> new RuntimeException("Suggested Point not found with id: " + request.getIdSuggestedPoint()));

        User user = userRepository.findById(request.getIdUser())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getIdUser()));

        ReportSuggestedPoint report = ReportSuggestedPointMapper.toEntity(request, typeOfReportPoint, suggestedPoint, user);
        ReportSuggestedPoint saved = repository.save(report);

        return ReportSuggestedPointMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<ReportSuggestedPointResponse> findAll() {
        return repository.findAll().stream()
                .map(ReportSuggestedPointMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReportSuggestedPointResponse getById(Integer id) {
        ReportSuggestedPoint report = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report Suggested Point not found with id: " + id));
        return ReportSuggestedPointMapper.toResponse(report);
    }

    @Transactional
    public ReportSuggestedPointResponse update(Integer id, ReportSuggestedPointRequest request) {
        ReportSuggestedPoint report = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report Suggested Point not found with id: " + id));

        TypeOfReportPoint typeOfReportPoint = typeOfReportPointRepository.findById(request.getIdTypeOfReportPoint())
                .orElseThrow(() -> new RuntimeException("Type of Report Point not found with id: " + request.getIdTypeOfReportPoint()));

        SuggestedPoint suggestedPoint = suggestedPointRepository.findById(request.getIdSuggestedPoint())
                .orElseThrow(() -> new RuntimeException("Suggested Point not found with id: " + request.getIdSuggestedPoint()));

        User user = userRepository.findById(request.getIdUser())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getIdUser()));

        ReportSuggestedPointMapper.copyToEntity(request, report, typeOfReportPoint, suggestedPoint, user);
        ReportSuggestedPoint updated = repository.save(report);

        return ReportSuggestedPointMapper.toResponse(updated);
    }

    @Transactional(readOnly = true)
    public List<ReportSuggestedPointResponse> getByUser(Integer userId) {
        return repository.findByUser_IdUser(userId).stream()
                .map(ReportSuggestedPointMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReportSuggestedPointResponse> getBySuggestedPoint(Integer suggestedPointId) {
        return repository.findBySuggestedPoint_IdSuggestedPoint(suggestedPointId).stream()
                .map(ReportSuggestedPointMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReportSuggestedPointResponse> getByType(Integer typeId) {
        return repository.findByTypeOfReportPoint_IdTypeOfReportPoint(typeId).stream()
                .map(ReportSuggestedPointMapper::toResponse)
                .collect(Collectors.toList());
    }
}