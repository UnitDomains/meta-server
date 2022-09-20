package domains.unit.metaserver.repository;

public interface BaseRegistrarEventNameRegisteredRepository {


    int getDomainNamesCount(int networkId);

    int getOwnersCount(int networkId);


}