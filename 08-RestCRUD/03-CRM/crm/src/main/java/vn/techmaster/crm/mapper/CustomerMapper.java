package vn.techmaster.crm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import vn.techmaster.crm.model.Customer;
import vn.techmaster.crm.model.CustomerPOJO;

/*
Sử dụng MapStruct để chuyển đổi giữa CustomerPOJO và Customer
*/
@Mapper
public interface CustomerMapper {
  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
  CustomerPOJO customerToPOJO(Customer customer);
  Customer pojoToCustomer(CustomerPOJO pojo);
}
