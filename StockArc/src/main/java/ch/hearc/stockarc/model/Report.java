package ch.hearc.stockarc.model;

import java.sql.Date;

public interface Report {

    Date getDay();
    Integer getTotalRent();
}
