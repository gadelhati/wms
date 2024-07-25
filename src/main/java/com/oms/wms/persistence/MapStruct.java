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
    DTOResponseAddress toDTO(Address address);
    DTOResponseCity toDTO(City city);
    DTOResponseCountry toDTO(Country country);
    DTOResponseDelivery toDTO(Delivery delivery);
    DTOResponseLot toDTO(Lot lot);
    DTOResponseOrder toDTO(Order order);
    DTOResponseOrderItem toDTO(OrderItem orderItem);
    DTOResponsePerson toDTO(Person person);
    DTOResponsePrivilege toDTO(Privilege privilege);
    DTOResponseItem toDTO(Item item);
    DTOResponseRole toDTO(Role role);
    DTOResponseState toDTO(State state);
    DTOResponseStock toDTO(Stock stock);
    DTOResponseUser toDTO(User user);

    Address toObject(DTORequestAddress dtoRequestAddress);
    City toObject(DTORequestCity dtoRequestCity);
    Country toObject(DTORequestCountry dtoRequestCountry);
    Delivery toObject(DTORequestDelivery dtoRequestDelivery);
    Lot toObject(DTORequestLot dtoRequestLot);
    Order toObject(DTORequestOrder dtoRequestOrder);
    OrderItem toObject(DTORequestOrderItem dtoRequestOrderItem);
    Person toObject(DTORequestPerson dtoRequestPerson);
    Privilege toObject(DTORequestPrivilege dtoRequestPrivilege);
    Item toObject(DTORequestItem dtoRequestItem);
    Role toObject(DTORequestRole dtoRequestRole);
    State toObject(DTORequestState dtoRequestState);
    Stock toObject(DTORequestStock dtoRequestStock);
    User toObject(DTORequestUser dtoRequestUser);
}