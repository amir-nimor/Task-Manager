package ir.maktabsharif.command;

import ir.maktabsharif.dao.BaseModel.BaseModel;

public interface Commandable <T extends BaseModel<ID>,ID extends Number,Q,S>{

    T requestToDao(Q q);

    S daoToResponse(T t);
}
