package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.AbsModel;
import vn.hcmuaf.edu.fit.IModel;

public abstract class AbsDao<T extends IModel> implements IDao {
    @Override
    public void select() {

    }

    @Override
    public boolean insert(AbsModel model, String ip, String level, String address) {
       LogDao.getInstance().insert(model, ip, level, address);
        return false;
    }

    @Override
    public boolean update(AbsModel model, String ip, String level, String address) {
        LogDao.getInstance().update(model, ip, level, address);
        return false;
    }

    @Override
    public boolean delete(AbsModel model, String ip, String level, String address) {
        LogDao.getInstance().delete(model, ip, level, address);
        return false;
    }
}
