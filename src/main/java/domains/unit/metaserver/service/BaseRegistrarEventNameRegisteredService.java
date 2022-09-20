package domains.unit.metaserver.service;

public interface BaseRegistrarEventNameRegisteredService {
    int getDomainNamesCount(int networkId);

    int getOwnersCount(int networkId);


}