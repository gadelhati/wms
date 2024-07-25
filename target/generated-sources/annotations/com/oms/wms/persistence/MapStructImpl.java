package com.oms.wms.persistence;

import com.oms.wms.persistence.model.Address;
import com.oms.wms.persistence.model.City;
import com.oms.wms.persistence.model.Country;
import com.oms.wms.persistence.model.Delivery;
import com.oms.wms.persistence.model.Item;
import com.oms.wms.persistence.model.Lot;
import com.oms.wms.persistence.model.Order;
import com.oms.wms.persistence.model.OrderItem;
import com.oms.wms.persistence.model.Person;
import com.oms.wms.persistence.model.Privilege;
import com.oms.wms.persistence.model.Role;
import com.oms.wms.persistence.model.State;
import com.oms.wms.persistence.model.Stock;
import com.oms.wms.persistence.model.User;
import com.oms.wms.persistence.payload.request.DTORequestAddress;
import com.oms.wms.persistence.payload.request.DTORequestCity;
import com.oms.wms.persistence.payload.request.DTORequestCountry;
import com.oms.wms.persistence.payload.request.DTORequestDelivery;
import com.oms.wms.persistence.payload.request.DTORequestItem;
import com.oms.wms.persistence.payload.request.DTORequestLot;
import com.oms.wms.persistence.payload.request.DTORequestOrder;
import com.oms.wms.persistence.payload.request.DTORequestOrderItem;
import com.oms.wms.persistence.payload.request.DTORequestPerson;
import com.oms.wms.persistence.payload.request.DTORequestPrivilege;
import com.oms.wms.persistence.payload.request.DTORequestRole;
import com.oms.wms.persistence.payload.request.DTORequestState;
import com.oms.wms.persistence.payload.request.DTORequestStock;
import com.oms.wms.persistence.payload.request.DTORequestUser;
import com.oms.wms.persistence.payload.response.DTOResponseAddress;
import com.oms.wms.persistence.payload.response.DTOResponseCity;
import com.oms.wms.persistence.payload.response.DTOResponseCountry;
import com.oms.wms.persistence.payload.response.DTOResponseDelivery;
import com.oms.wms.persistence.payload.response.DTOResponseItem;
import com.oms.wms.persistence.payload.response.DTOResponseLot;
import com.oms.wms.persistence.payload.response.DTOResponseOrder;
import com.oms.wms.persistence.payload.response.DTOResponseOrderItem;
import com.oms.wms.persistence.payload.response.DTOResponsePerson;
import com.oms.wms.persistence.payload.response.DTOResponsePrivilege;
import com.oms.wms.persistence.payload.response.DTOResponseRole;
import com.oms.wms.persistence.payload.response.DTOResponseState;
import com.oms.wms.persistence.payload.response.DTOResponseStock;
import com.oms.wms.persistence.payload.response.DTOResponseUser;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-25T15:33:05-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class MapStructImpl implements MapStruct {

    @Override
    public DTOResponseAddress toDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        UUID id = null;
        String cepNumber = null;
        String cepCategory = null;
        String cepSubCategory = null;
        String neighborhood = null;
        String address1 = null;
        String complement = null;
        String iBGECode = null;
        City city = null;

        id = address.getId();
        cepNumber = address.getCepNumber();
        cepCategory = address.getCepCategory();
        cepSubCategory = address.getCepSubCategory();
        neighborhood = address.getNeighborhood();
        address1 = address.getAddress();
        complement = address.getComplement();
        iBGECode = address.getIBGECode();
        city = address.getCity();

        DTOResponseAddress dTOResponseAddress = new DTOResponseAddress( id, cepNumber, cepCategory, cepSubCategory, neighborhood, address1, complement, iBGECode, city );

        return dTOResponseAddress;
    }

    @Override
    public DTOResponseCity toDTO(City city) {
        if ( city == null ) {
            return null;
        }

        UUID id = null;
        String code = null;
        String name = null;
        State state = null;

        id = city.getId();
        code = city.getCode();
        name = city.getName();
        state = city.getState();

        DTOResponseCity dTOResponseCity = new DTOResponseCity( id, code, name, state );

        return dTOResponseCity;
    }

    @Override
    public DTOResponseCountry toDTO(Country country) {
        if ( country == null ) {
            return null;
        }

        UUID id = null;
        String name = null;

        id = country.getId();
        name = country.getName();

        DTOResponseCountry dTOResponseCountry = new DTOResponseCountry( id, name );

        return dTOResponseCountry;
    }

    @Override
    public DTOResponseDelivery toDTO(Delivery delivery) {
        if ( delivery == null ) {
            return null;
        }

        UUID id = null;
        String status = null;
        LocalDateTime statusDate = null;

        id = delivery.getId();
        status = delivery.getStatus();
        statusDate = delivery.getStatusDate();

        DTOResponseDelivery dTOResponseDelivery = new DTOResponseDelivery( id, status, statusDate );

        return dTOResponseDelivery;
    }

    @Override
    public DTOResponseLot toDTO(Lot lot) {
        if ( lot == null ) {
            return null;
        }

        UUID id = null;
        String number = null;
        LocalDateTime manufacturing = null;
        LocalDateTime overdue = null;

        id = lot.getId();
        number = lot.getNumber();
        manufacturing = lot.getManufacturing();
        overdue = lot.getOverdue();

        DTOResponseLot dTOResponseLot = new DTOResponseLot( id, number, manufacturing, overdue );

        return dTOResponseLot;
    }

    @Override
    public DTOResponseOrder toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        Collection<OrderItem> orderItem = null;
        UUID id = null;
        String category = null;
        float totalCost = 0.0f;

        Collection<OrderItem> collection = order.getOrderItem();
        if ( collection != null ) {
            orderItem = new ArrayList<OrderItem>( collection );
        }
        id = order.getId();
        if ( order.getCategory() != null ) {
            category = order.getCategory().name();
        }
        totalCost = order.getTotalCost();

        Collection<Delivery> delivery = null;
        Person seller = null;
        Person buyer = null;
        Stock stockSeller = null;
        Stock stockBuyer = null;

        DTOResponseOrder dTOResponseOrder = new DTOResponseOrder( id, category, totalCost, orderItem, delivery, seller, buyer, stockSeller, stockBuyer );

        return dTOResponseOrder;
    }

    @Override
    public DTOResponseOrderItem toDTO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        UUID id = null;
        float unitPrice = 0.0f;
        float discount = 0.0f;
        int quantity = 0;
        float totalCost = 0.0f;
        Item item = null;

        id = orderItem.getId();
        unitPrice = orderItem.getUnitPrice();
        discount = orderItem.getDiscount();
        quantity = orderItem.getQuantity();
        totalCost = orderItem.getTotalCost();
        item = orderItem.getItem();

        DTOResponseOrderItem dTOResponseOrderItem = new DTOResponseOrderItem( id, unitPrice, discount, quantity, totalCost, item );

        return dTOResponseOrderItem;
    }

    @Override
    public DTOResponsePerson toDTO(Person person) {
        if ( person == null ) {
            return null;
        }

        Collection<String> telephone = null;
        Collection<Address> address = null;
        UUID id = null;
        String name = null;
        LocalDateTime birth = null;
        String email = null;
        Country country = null;

        Collection<String> collection = person.getTelephone();
        if ( collection != null ) {
            telephone = new ArrayList<String>( collection );
        }
        Collection<Address> collection1 = person.getAddress();
        if ( collection1 != null ) {
            address = new ArrayList<Address>( collection1 );
        }
        id = person.getId();
        name = person.getName();
        birth = person.getBirth();
        email = person.getEmail();
        country = person.getCountry();

        DTOResponsePerson dTOResponsePerson = new DTOResponsePerson( id, name, birth, email, telephone, country, address );

        return dTOResponsePerson;
    }

    @Override
    public DTOResponsePrivilege toDTO(Privilege privilege) {
        if ( privilege == null ) {
            return null;
        }

        UUID id = null;
        String name = null;

        id = privilege.getId();
        name = privilege.getName();

        DTOResponsePrivilege dTOResponsePrivilege = new DTOResponsePrivilege( id, name );

        return dTOResponsePrivilege;
    }

    @Override
    public DTOResponseItem toDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        UUID id = null;
        String sku = null;
        String gtin = null;
        String category = null;
        String brand = null;
        String model = null;
        int minimumStock = 0;
        int maximumStock = 0;
        int reservedStock = 0;
        int availableStock = 0;
        int bulk = 0;
        int grossWeightMeasurement = 0;
        int netWeightMeasurement = 0;
        int grossWeight = 0;
        int netWeight = 0;
        String url = null;
        Lot lot = null;

        id = item.getId();
        sku = item.getSku();
        gtin = item.getGtin();
        category = item.getCategory();
        brand = item.getBrand();
        model = item.getModel();
        minimumStock = item.getMinimumStock();
        maximumStock = item.getMaximumStock();
        reservedStock = item.getReservedStock();
        availableStock = item.getAvailableStock();
        bulk = item.getBulk();
        grossWeightMeasurement = item.getGrossWeightMeasurement();
        netWeightMeasurement = item.getNetWeightMeasurement();
        grossWeight = item.getGrossWeight();
        netWeight = item.getNetWeight();
        url = item.getUrl();
        lot = item.getLot();

        DTOResponseItem dTOResponseItem = new DTOResponseItem( id, sku, gtin, category, brand, model, minimumStock, maximumStock, reservedStock, availableStock, bulk, grossWeightMeasurement, netWeightMeasurement, grossWeight, netWeight, url, lot );

        return dTOResponseItem;
    }

    @Override
    public DTOResponseRole toDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        UUID id = null;
        String name = null;

        id = role.getId();
        name = role.getName();

        DTOResponseRole dTOResponseRole = new DTOResponseRole( id, name );

        return dTOResponseRole;
    }

    @Override
    public DTOResponseState toDTO(State state) {
        if ( state == null ) {
            return null;
        }

        UUID id = null;
        String code = null;
        String name = null;
        Country country = null;

        id = state.getId();
        code = state.getCode();
        name = state.getName();
        country = state.getCountry();

        DTOResponseState dTOResponseState = new DTOResponseState( id, code, name, country );

        return dTOResponseState;
    }

    @Override
    public DTOResponseStock toDTO(Stock stock) {
        if ( stock == null ) {
            return null;
        }

        UUID id = null;
        int maximumBulk = 0;
        int currentBulk = 0;

        id = stock.getId();
        maximumBulk = stock.getMaximumBulk();
        currentBulk = stock.getCurrentBulk();

        DTOResponseStock dTOResponseStock = new DTOResponseStock( id, maximumBulk, currentBulk );

        return dTOResponseStock;
    }

    @Override
    public DTOResponseUser toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        Collection<Role> role = null;
        UUID id = null;
        String username = null;
        String email = null;
        String password = null;
        Boolean active = null;

        Collection<Role> collection = user.getRole();
        if ( collection != null ) {
            role = new ArrayList<Role>( collection );
        }
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        password = user.getPassword();
        active = user.getActive();

        DTOResponseUser dTOResponseUser = new DTOResponseUser( id, username, email, password, active, role );

        return dTOResponseUser;
    }

    @Override
    public Address toObject(DTORequestAddress dtoRequestAddress) {
        if ( dtoRequestAddress == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( dtoRequestAddress.getId() );
        address.setCepNumber( dtoRequestAddress.getCepNumber() );
        address.setCepCategory( dtoRequestAddress.getCepCategory() );
        address.setCepSubCategory( dtoRequestAddress.getCepSubCategory() );
        address.setNeighborhood( dtoRequestAddress.getNeighborhood() );
        address.setAddress( dtoRequestAddress.getAddress() );
        address.setComplement( dtoRequestAddress.getComplement() );
        address.setIBGECode( dtoRequestAddress.getIBGECode() );
        address.setCity( dtoRequestAddress.getCity() );

        return address;
    }

    @Override
    public City toObject(DTORequestCity dtoRequestCity) {
        if ( dtoRequestCity == null ) {
            return null;
        }

        City city = new City();

        city.setId( dtoRequestCity.getId() );
        city.setCode( dtoRequestCity.getCode() );
        city.setName( dtoRequestCity.getName() );
        city.setState( dtoRequestCity.getState() );

        return city;
    }

    @Override
    public Country toObject(DTORequestCountry dtoRequestCountry) {
        if ( dtoRequestCountry == null ) {
            return null;
        }

        Country country = new Country();

        country.setId( dtoRequestCountry.getId() );
        country.setName( dtoRequestCountry.getName() );

        return country;
    }

    @Override
    public Delivery toObject(DTORequestDelivery dtoRequestDelivery) {
        if ( dtoRequestDelivery == null ) {
            return null;
        }

        Delivery delivery = new Delivery();

        delivery.setId( dtoRequestDelivery.getId() );
        delivery.setStatus( dtoRequestDelivery.getStatus() );
        delivery.setStatusDate( dtoRequestDelivery.getStatusDate() );

        return delivery;
    }

    @Override
    public Lot toObject(DTORequestLot dtoRequestLot) {
        if ( dtoRequestLot == null ) {
            return null;
        }

        Lot lot = new Lot();

        lot.setId( dtoRequestLot.getId() );
        lot.setNumber( dtoRequestLot.getNumber() );
        lot.setManufacturing( dtoRequestLot.getManufacturing() );
        lot.setOverdue( dtoRequestLot.getOverdue() );

        return lot;
    }

    @Override
    public Order toObject(DTORequestOrder dtoRequestOrder) {
        if ( dtoRequestOrder == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( dtoRequestOrder.getId() );
        if ( dtoRequestOrder.getCategory() != null ) {
            order.setCategory( Enum.valueOf( OrderCategory.class, dtoRequestOrder.getCategory() ) );
        }
        order.setTotalCost( dtoRequestOrder.getTotalCost() );
        Collection<OrderItem> collection = dtoRequestOrder.getOrderItem();
        if ( collection != null ) {
            order.setOrderItem( new ArrayList<OrderItem>( collection ) );
        }

        return order;
    }

    @Override
    public OrderItem toObject(DTORequestOrderItem dtoRequestOrderItem) {
        if ( dtoRequestOrderItem == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setId( dtoRequestOrderItem.getId() );
        orderItem.setUnitPrice( dtoRequestOrderItem.getUnitPrice() );
        orderItem.setDiscount( dtoRequestOrderItem.getDiscount() );
        orderItem.setQuantity( dtoRequestOrderItem.getQuantity() );
        orderItem.setTotalCost( dtoRequestOrderItem.getTotalCost() );
        orderItem.setItem( dtoRequestOrderItem.getItem() );

        return orderItem;
    }

    @Override
    public Person toObject(DTORequestPerson dtoRequestPerson) {
        if ( dtoRequestPerson == null ) {
            return null;
        }

        Person person = new Person();

        person.setId( dtoRequestPerson.getId() );
        person.setName( dtoRequestPerson.getName() );
        person.setBirth( dtoRequestPerson.getBirth() );
        person.setEmail( dtoRequestPerson.getEmail() );
        Collection<String> collection = dtoRequestPerson.getTelephone();
        if ( collection != null ) {
            person.setTelephone( new ArrayList<String>( collection ) );
        }
        person.setCountry( dtoRequestPerson.getCountry() );
        Collection<Address> collection1 = dtoRequestPerson.getAddress();
        if ( collection1 != null ) {
            person.setAddress( new ArrayList<Address>( collection1 ) );
        }

        return person;
    }

    @Override
    public Privilege toObject(DTORequestPrivilege dtoRequestPrivilege) {
        if ( dtoRequestPrivilege == null ) {
            return null;
        }

        Privilege privilege = new Privilege();

        privilege.setId( dtoRequestPrivilege.getId() );
        privilege.setName( dtoRequestPrivilege.getName() );

        return privilege;
    }

    @Override
    public Item toObject(DTORequestItem dtoRequestItem) {
        if ( dtoRequestItem == null ) {
            return null;
        }

        Item item = new Item();

        item.setId( dtoRequestItem.getId() );
        item.setSku( dtoRequestItem.getSku() );
        item.setGtin( dtoRequestItem.getGtin() );
        item.setCategory( dtoRequestItem.getCategory() );
        item.setBrand( dtoRequestItem.getBrand() );
        item.setModel( dtoRequestItem.getModel() );
        item.setMinimumStock( dtoRequestItem.getMinimumStock() );
        item.setMaximumStock( dtoRequestItem.getMaximumStock() );
        item.setReservedStock( dtoRequestItem.getReservedStock() );
        item.setAvailableStock( dtoRequestItem.getAvailableStock() );
        item.setBulk( dtoRequestItem.getBulk() );
        item.setGrossWeightMeasurement( dtoRequestItem.getGrossWeightMeasurement() );
        item.setNetWeightMeasurement( dtoRequestItem.getNetWeightMeasurement() );
        item.setGrossWeight( dtoRequestItem.getGrossWeight() );
        item.setNetWeight( dtoRequestItem.getNetWeight() );
        item.setUrl( dtoRequestItem.getUrl() );
        item.setLot( dtoRequestItem.getLot() );

        return item;
    }

    @Override
    public Role toObject(DTORequestRole dtoRequestRole) {
        if ( dtoRequestRole == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( dtoRequestRole.getId() );
        role.setName( dtoRequestRole.getName() );

        return role;
    }

    @Override
    public State toObject(DTORequestState dtoRequestState) {
        if ( dtoRequestState == null ) {
            return null;
        }

        State state = new State();

        state.setId( dtoRequestState.getId() );
        state.setCode( dtoRequestState.getCode() );
        state.setName( dtoRequestState.getName() );
        state.setCountry( dtoRequestState.getCountry() );

        return state;
    }

    @Override
    public Stock toObject(DTORequestStock dtoRequestStock) {
        if ( dtoRequestStock == null ) {
            return null;
        }

        Stock stock = new Stock();

        stock.setId( dtoRequestStock.getId() );
        stock.setMaximumBulk( dtoRequestStock.getMaximumBulk() );
        stock.setCurrentBulk( dtoRequestStock.getCurrentBulk() );

        return stock;
    }

    @Override
    public User toObject(DTORequestUser dtoRequestUser) {
        if ( dtoRequestUser == null ) {
            return null;
        }

        User user = new User();

        user.setId( dtoRequestUser.getId() );
        user.setUsername( dtoRequestUser.getUsername() );
        user.setEmail( dtoRequestUser.getEmail() );
        user.setPassword( dtoRequestUser.getPassword() );
        user.setActive( dtoRequestUser.isActive() );
        Collection<Role> collection = dtoRequestUser.getRole();
        if ( collection != null ) {
            user.setRole( new ArrayList<Role>( collection ) );
        }

        return user;
    }
}
