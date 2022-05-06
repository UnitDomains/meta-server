package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.EthRegistrarControllerEventNameRenewed;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EthRegistrarControllerEventNameRenewedRepository;
import domains.unit.metaserver.service.EthRegistrarControllerEventNameRenewedService;
import org.springframework.stereotype.Service;

@Service
public class EthRegistrarControllerEventNameRenewedServiceImpl implements EthRegistrarControllerEventNameRenewedService {
	private final EthRegistrarControllerEventNameRenewedRepository ethRegistrarControllerEventNameRenewedRepository;

	public EthRegistrarControllerEventNameRenewedServiceImpl(EthRegistrarControllerEventNameRenewedRepository ethRegistrarControllerEventNameRenewedRepository) {
		this.ethRegistrarControllerEventNameRenewedRepository = ethRegistrarControllerEventNameRenewedRepository;
	}


	@Override
	public int getCount() {
		return ethRegistrarControllerEventNameRenewedRepository.getCount();
	}

	/**
	 * 根据pkId得到EthRegistrarControllerEventNameRenewed
	 *
	 * @param pkId
	 */
	@Override
	public EthRegistrarControllerEventNameRenewed getByPkId(String pkId) {
		return ethRegistrarControllerEventNameRenewedRepository.getByPkId(pkId);
	}

	/**
	 * 获得指定页面数据
	 *
	 * @param pageNo   页号，从1开始
	 * @param pageSize 每页的记录数
	 */
	@Override
	public Page<EthRegistrarControllerEventNameRenewed> getPage(int pageNo, int pageSize) {
		return ethRegistrarControllerEventNameRenewedRepository.getPage(pageNo, pageSize);
	}

}