package cn.yu.cartoon.serviceImpl;

import cn.yu.cartoon.service.CartoonService;
import cn.yu.cartoon.utils.RandomUtils;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/14 20:57
 **/
@Service("cartoonService")
class CartoonServiceImpl implements CartoonService {

    @Override
    public boolean uploadCartoonByZipFile(File zipFile) {

        String dirName = RandomUtils.randomString(8);


        return false;
    }
}
