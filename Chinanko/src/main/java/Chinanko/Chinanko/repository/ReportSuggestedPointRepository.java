package chinanko.chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.ReportSuggestedPoint;

import java.util.List;

@Repository
public interface ReportSuggestedPointRepository extends JpaRepository<ReportSuggestedPoint, Integer> {
    
    // Buscar reportes por usuario
    List<ReportSuggestedPoint> findByUser_IdUser(Integer userId);
    
    // Buscar reportes por punto sugerido
    List<ReportSuggestedPoint> findBySuggestedPoint_IdSuggestedPoint(Integer suggestedPointId);
    
    // Buscar reportes por tipo de reporte
    List<ReportSuggestedPoint> findByTypeOfReportPoint_IdTypeOfReportPoint(Integer typeId);
}