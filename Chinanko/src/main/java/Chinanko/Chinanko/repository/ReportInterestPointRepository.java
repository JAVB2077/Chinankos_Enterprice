package chinanko.chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.ReportInterestPoint;

import java.util.List;

@Repository
public interface ReportInterestPointRepository extends JpaRepository<ReportInterestPoint, Integer> {
    
    // Buscar reportes por usuario
    List<ReportInterestPoint> findByUser_IdUser(Integer userId);
    
    // Buscar reportes por punto de interés
    List<ReportInterestPoint> findByInterestPoint_IdInterestPoint(Integer interestPointId);
    
    // Buscar reportes por tipo de reporte
    List<ReportInterestPoint> findByTypeOfReportPoint_IdTypeOfReportPoint(Integer typeId);
}