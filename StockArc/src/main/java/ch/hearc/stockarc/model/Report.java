package ch.hearc.stockarc.model;

import java.sql.Date;

/**
 * Interface that represent a report
 * 
 * @author Alexandre Bianchi
 */

public interface Report {

    /**
     * Get the day of the report
     * 
     * @return The day
     */
    Date getDay();

    /**
     * Get the total amount of rent of this report
     * 
     * @return The amount of rent
     */
    Integer getTotalRent();
}
