package ch.hearc.stockarc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ch.hearc.stockarc.model.Rent;
import ch.hearc.stockarc.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Rent, Long> {

    @Query("SELECT DATE(createdAt) AS day, COUNT(id) as totalRent FROM Rent GROUP BY day ORDER BY day DESC")
    List<Report> groupReportByDate();

}