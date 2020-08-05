package com.xcs.phase2.dao.other;


import com.xcs.phase2.model.other.RemainProduct;
import com.xcs.phase2.response.Other.RemainProductinsAllResponse;

public interface RemainProductDAO {

    public RemainProductinsAllResponse RemainProductinsAll(RemainProduct req);
}

