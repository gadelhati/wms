package com.oms.wms.persistence;

import com.oms.wms.persistence.model.*;
import com.oms.wms.persistence.payload.request.*;
import com.oms.wms.persistence.payload.response.*;
import com.oms.wms.persistence.model.*;
import com.oms.wms.persistence.payload.request.*;
import com.oms.wms.persistence.payload.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface MapStruct {

    MapStruct MAPPER = Mappers.getMapper(MapStruct.class);
    DTOResponseCity toDTO(City city);
    DTOResponseCountry toDTO(Country country);
    DTOResponsePrivilege toDTO(Privilege privilege);
    DTOResponseState toDTO(State state);
    DTOResponseRole toDTO(Role role);
    DTOResponseUser toDTO(User user);
    DTOResponseOrder toDTO(Order order);
    DTOResponseOrderItem toDTO(OrderItem orderItem);
    DTOResponseStock toDTO(Stock stock);
    DTOResponseProduct toDTO(Product product);
    DTOResponseDelivery toDTO(Delivery delivery);

    City toObject(DTORequestCity dtoRequestCity);
    Country toObject(DTORequestCountry dtoRequestCountry);
    Privilege toObject(DTORequestPrivilege dtoRequestPrivilege);
    State toObject(DTORequestState dtoRequestState);
    Role toObject(DTORequestRole dtoRequestRole);
    User toObject(DTORequestUser dtoRequestUser);
    Order toObject(DTORequestOrder dtoRequestOrder);
    OrderItem toObject(DTORequestOrderItem dtoRequestOrderItem);
    Stock toObject(DTORequestStock dtoRequestStock);
    Product toObject(DTORequestProduct dtoRequestProduct);
    Delivery toObject(DTORequestDelivery dtoRequestDelivery);
}