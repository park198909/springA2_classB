package org.koreait.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 100952260L;

    public static final QOrder order = new QOrder("order1");

    public final StringPath gid = createString("gid");

    public final StringPath orderId = createString("orderId");

    public final StringPath orderlist = createString("orderlist");

    public final NumberPath<Long> orderNo = createNumber("orderNo", Long.class);

    public final StringPath orderStat = createString("orderStat");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public QOrder(String variable) {
        super(Order.class, forVariable(variable));
    }

    public QOrder(Path<? extends Order> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrder(PathMetadata metadata) {
        super(Order.class, metadata);
    }

}

