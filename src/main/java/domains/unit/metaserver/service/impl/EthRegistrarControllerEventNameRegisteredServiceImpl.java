package domains.unit.webserver.service.impl;

import domains.unit.metaserver.model.EthRegistrarControllerEventNameRegistered;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EthRegistrarControllerEventNameRegisteredRepository;
import domains.unit.metaserver.service.EthRegistrarControllerEventNameRegisteredService;
import org.springframework.stereotype.Service;

@Service
public class EthRegistrarControllerEventNameRegisteredServiceImpl implements EthRegistrarControllerEventNameRegisteredService {
	private final EthRegistrarControllerEventNameRegisteredRepository ethRegistrarControllerEventNameRegisteredRepository;

	public EthRegistrarControllerEventNameRegisteredServiceImpl(EthRegistrarControllerEventNameRegisteredRepository ethRegistrarControllerEventNameRegisteredRepository) {
		this.ethRegistrarControllerEventNameRegisteredRepository = ethRegistrarControllerEventNameRegisteredRepository;
	}


	@Override
	public int getCount() {
		return ethRegistrarControllerEventNameRegisteredRepository.getCount();
	}

	/**
	 * 根据pkId得到EthRegistrarControllerEventNameRegistered
	 *
	 * @param pkId
	 */
	@Override
	public EthRegistrarControllerEventNameRegistered getByPkId(String pkId) {
		return ethRegistrarControllerEventNameRegisteredRepository.getByPkId(pkId);
	}

	/**
	 * 获得指定页面数据
	 *
	 * @param pageNo   页号，从1开始
	 * @param pageSize 每页的记录数
	 */
	@Override
	public Page<EthRegistrarControllerEventNameRegistered> getPage(int pageNo, int pageSize) {
		return ethRegistrarControllerEventNameRegisteredRepository.getPage(pageNo, pageSize);
	}

}