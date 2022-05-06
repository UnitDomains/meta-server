package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.EnsRegistryEventTransfer;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EnsRegistryEventTransferRepository;
import domains.unit.metaserver.service.EnsRegistryEventTransferService;
import org.springframework.stereotype.Service;

@Service
public class EnsRegistryEventTransferServiceImpl implements EnsRegistryEventTransferService {
	private final EnsRegistryEventTransferRepository ensRegistryEventTransferRepository;

	public EnsRegistryEventTransferServiceImpl(EnsRegistryEventTransferRepository ensRegistryEventTransferRepository) {
		this.ensRegistryEventTransferRepository = ensRegistryEventTransferRepository;
	}


	@Override
	public int getCount() {
		return ensRegistryEventTransferRepository.getCount();
	}

	/**
	 * 根据pkId得到EnsRegistryEventTransfer
	 *
	 * @param pkId
	 */
	@Override
	public EnsRegistryEventTransfer getByPkId(String pkId) {
		return ensRegistryEventTransferRepository.getByPkId(pkId);
	}

	/**
	 * 获得指定页面数据
	 *
	 * @param pageNo   页号，从1开始
	 * @param pageSize 每页的记录数
	 */
	@Override
	public Page<EnsRegistryEventTransfer> getPage(int pageNo, int pageSize) {
		return ensRegistryEventTransferRepository.getPage(pageNo, pageSize);
	}

}