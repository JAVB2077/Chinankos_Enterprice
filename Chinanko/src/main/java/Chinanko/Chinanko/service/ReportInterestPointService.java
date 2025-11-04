package chinanko.chinanko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.ReportInterestPointRequest;
import chinanko.chinanko.dto.ReportInterestPointResponse;
import chinanko.chinanko.mapper.ReportInterestPointMapper;
import chinanko.chinanko.model.InterestPoint;
import chinanko.chinanko.model.ReportInterestPoint;
import chinanko.chinanko.model.TypeOfReportPoint;
import chinanko.chinanko.model.User;
import chinanko.chinanko.repository.InterestPointRepository;
import chinanko.chinanko.repository.ReportInterestPointRepository;
import chinanko.chinanko.repository.TypeOfReportPointRepository;
import chinanko.chinanko.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportInterestPointService {

    private final ReportInterestPointRepository repository;
    private final TypeOfReportPointRepository typeOfReportPointRepository;
    private final InterestPointRepository interestPointRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReportInterestPointResponse create(ReportInterestPointRequest request) {
        TypeOfReportPoint typeOfReportPoint = typeOfReportPointRepository.findById(request.getIdTypeOfReportPoint())
                .orElseThrow(() -> new RuntimeException("Type of Report Point not found with id: " + request.getIdTypeOfReportPoint()));

        InterestPoint interestPoint = interestPointRepository.findById(request.getIdInterestPoint())
                .orElseThrow(() -> new RuntimeException("Interest Point not found with id: " + request.getIdInterestPoint()));

        User user = userRepository.findById(request.getIdUser())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getIdUser()));

        ReportInterestPoint report = ReportInterestPointMapper.toEntity(request, typeOfReportPoint, interestPoint, user);
        ReportInterestPoint saved = repository.save(report);

        return ReportInterestPointMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<ReportInterestPointResponse> findAll() {
        return repository.findAll().stream()
                .map(ReportInterestPointMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReportInterestPointResponse getById(Integer id) {
        ReportInterestPoint report = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report Interest Point not found with id: " + id));
        return ReportInterestPointMapper.toResponse(report);
    }

    @Transactional
    public ReportInterestPointResponse update(Integer id, ReportInterestPointRequest request) {
        ReportInterestPoint report = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report Interest Point not found with id: " + id));

        TypeOfReportPoint typeOfReportPoint = typeOfReportPointRepository.findById(request.getIdTypeOfReportPoint())
                .orElseThrow(() -> new RuntimeException("Type of Report Point not found with id: " + request.getIdTypeOfReportPoint()));

        InterestPoint interestPoint = interestPointRepository.findById(request.getIdInterestPoint())
                .orElseThrow(() -> new RuntimeException("Interest Point not found with id: " + request.getIdInterestPoint()));

        User user = userRepository.findById(request.getIdUser())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getIdUser()));

        ReportInterestPointMapper.copyToEntity(request, report, typeOfReportPoint, interestPoint, user);
        ReportInterestPoint updated = repository.save(report);

        return ReportInterestPointMapper.toResponse(updated);
    }

    @Transactional(readOnly = true)
    public List<ReportInterestPointResponse> getByUser(Integer userId) {
        return repository.findByUser_IdUser(userId).stream()
                .map(ReportInterestPointMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReportInterestPointResponse> getByInterestPoint(Integer interestPointId) {
        return repository.findByInterestPoint_IdInterestPoint(interestPointId).stream()
                .map(ReportInterestPointMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReportInterestPointResponse> getByType(Integer typeId) {
        return repository.findByTypeOfReportPoint_IdTypeOfReportPoint(typeId).stream()
                .map(ReportInterestPointMapper::toResponse)
                .collect(Collectors.toList());
    }
}