package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.util.*;

public class Covid19ManagerImpl implements Covid19Manager {
    private static Covid19Manager instance;

    protected HashMap<String, User> users;
    protected List<Vaccine> vaccinesList;
    protected Trademark[] trademarksList;
    private int numTrademarks;
    protected List<Tracing> tracingsList;

    final static Logger logger = Logger.getLogger(Covid19ManagerImpl.class);

    private Covid19ManagerImpl() {
        this.users = new HashMap<>();
        this.vaccinesList = new LinkedList<>();
        this.trademarksList = new Trademark[100];
        this.numTrademarks = 0;
        this.tracingsList = new LinkedList<>();
    }

    public static Covid19Manager getInstance() {
        if (instance==null) instance = new Covid19ManagerImpl();
        return instance;
    }

    @Override
    public void applyVaccine(Vaccine vaccine) {
        vaccinesList.add(vaccine);
        logger.info("New vaccine added:" + vaccine);
    }

    @Override
    public List<Vaccine> getVaccinesByTrademark() {
        logger.info("Query: getVaccinesByTrademark. Before ordering:" + vaccinesList);
        Collections.sort(this.vaccinesList, new Comparator<Vaccine>() {
            @Override
            public int compare(Vaccine p1, Vaccine p2) {
                if(p1.getTrademark().compareTo(p2.getTrademark()) < 0) return -1;
                else if (p1.getTrademark().compareTo(p2.getTrademark()) > 0) return 1;
                else {
                    if(p1.getDate().compareTo(p2.getDate()) < 0) return -1;
                    else if(p1.getDate().compareTo(p2.getDate()) > 0) return 1;
                    else return 0;
                }
            }
        });
        logger.info("After ordering:" + vaccinesList);
        return vaccinesList;
    }

    @Override
    public Trademark[] getTrademarksByAppliedVaccines() {
        logger.info("Query: getTrademarksByAppliedVaccines. Before ordering:" + trademarksList);
        Collections.sort(Arrays.asList(this.trademarksList), new Comparator<Trademark>() {
            @Override
            public int compare(Trademark p1, Trademark p2) {
                return (p1.getAppliedVaccines() - p2.getAppliedVaccines());
            }
        });
        logger.info("After ordering:" + trademarksList);
        return trademarksList;
    }

    @Override
    public void addTracing(Tracing tracing) {
        tracingsList.add(tracing);
        logger.info("New tracing added:" + tracing);
    }

    @Override
    public List<Tracing> getTracingsForUser(String id_user) {
        List<Tracing> list = new LinkedList<>();
        for(int i = 0; i < tracingsList.size(); i++) {
            if(id_user.equals(tracingsList.get(i).getUser_id())) list.add(tracingsList.get(i));
        }
        logger.info("Query: getTracingsForUser. Returns:" + list);
        return list;
    }

    @Override
    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void addTrademark(Trademark trademark) {
        /*if(trademarksList[0] == null) trademarksList[0] = trademark;
        else{
            int i = 0;
            boolean encontrado = false;
            while (i < trademarksList.length - 1 && !encontrado) {
                if (trademarksList[i] == null) encontrado = true;
                i++;
            }
            trademarksList[i] = trademark;
        }*/
        trademarksList[numTrademarks] = trademark;
        numTrademarks++;
    }

    @Override
    public void clear() {
        users.clear();
        vaccinesList.clear();
        tracingsList.clear();
        trademarksList = null;
    }
}
