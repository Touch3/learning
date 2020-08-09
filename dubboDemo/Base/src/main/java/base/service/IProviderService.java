package base.service;

import base.dto.ProviderDTO;

import java.util.List;

/**
 * RPC接口
 * @Author siteFound
 * @CreateTime 2020/08/092
 */
public interface IProviderService {

    List<ProviderDTO> queryList();
}