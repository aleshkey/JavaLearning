package com.shop.carshop.Repository;

import com.shop.carshop.model.*;

public class ImageRepository implements Repository<Image>{

    @Override
    public Class<Image> getModelClass() {
        return Image.class;
    }

}
