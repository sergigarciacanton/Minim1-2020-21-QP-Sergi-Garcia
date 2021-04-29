package edu.upc.dsa;

import edu.upc.dsa.models.Tracing;
import edu.upc.dsa.models.Trademark;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.Vaccine;

import java.util.List;

public interface Covid19Manager {

    void applyVaccine(Vaccine vaccine);
    List<Vaccine> getVaccinesByTrademark();
    Trademark[] getTrademarksByAppliedVaccines();
    void addTracing(Tracing tracing);
    List<Tracing> getTracingsForUser(String id_user);

    void addUser(User user);
    void addTrademark(Trademark trademark);
    void clear();

}
