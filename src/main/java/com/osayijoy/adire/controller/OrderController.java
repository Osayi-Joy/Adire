package com.osayijoy.adire.controller;
import com.osayijoy.adire.dto.request.OrderDTO;
import com.osayijoy.adire.dto.response.AppResponse;
import com.osayijoy.adire.dto.response.OrderResponse;
import com.osayijoy.adire.dto.response.PaginationResponse;
import com.osayijoy.adire.service.CustomerService;
import com.osayijoy.adire.service.OrderService;
import com.osayijoy.adire.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;

    @GetMapping("/getAllOrders")
    public ResponseEntity<Object> getAllOrders(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            HttpServletRequest request
    ){
        PaginationResponse orderResponse = orderService.fetchAllOrders(request, pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.ok().body(AppResponse.buildSuccessTxn(orderResponse));
    }


    @GetMapping("/get-a-single-order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id){
        OrderDTO orderDTO = orderService.findOrderById(id);
        return ResponseEntity.ok().body(AppResponse.buildSuccessTxn(mapToResponse(orderDTO)));
    }
    @GetMapping("/count")
    public ResponseEntity<?> countOders(HttpServletRequest request){
        log.info("called end point");
        return new ResponseEntity<>(orderService.countOrders(request),HttpStatus.OK);
    }

    @PostMapping("/create-order")
    public ResponseEntity<AppResponse<?>> registerCustomer(@RequestBody @Valid final OrderDTO orderDTO, HttpServletRequest request) {
        log.info("controller create order: create order for :: [{}] ::", orderDTO.getCustomerEmail());
        OrderResponse response = orderService.createOrder(orderDTO, request);
        return new ResponseEntity<>(AppResponse.buildSuccess(response), HttpStatus.CREATED);

    }
    @GetMapping(path = "/report")
    public ResponseEntity<?>  getAllRevenueReport(HttpServletRequest request) {
        return new ResponseEntity<>(orderService.getAllRevenueReport(request), HttpStatus.OK);
    }
    @GetMapping(path = "/monthly-Chart-report")
    public ResponseEntity<?> getMonthlyReport(Principal principal, HttpServletRequest request){
        return new ResponseEntity<>(orderService.getMonthlyReport(request, principal), HttpStatus.OK);
    }

    @PutMapping(path = "/updateOrder/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable("id") String id, @RequestBody OrderDTO request){
        OrderResponse updateOrder = orderService.updateOrder(request, id);
        return ResponseEntity.ok().body(AppResponse.buildSuccessTxn(updateOrder));
    }

    @DeleteMapping(path = "/deleteOrder/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("id") String id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok().body(AppResponse.buildSuccessTxn("Order deleted successfully"));
    }

    private OrderResponse mapToResponse(OrderDTO orderDTO) {
        return OrderResponse.builder()
                .id(orderDTO.getId())
                .customerEmail(orderDTO.getCustomerEmail())
                .customer(customerService.getCustomerByEmail(orderDTO.getCustomerEmail()))
                .orderStatus(orderDTO.getOrderStatus())
                .measurementList(orderDTO.getMeasurementList())
                .orderPrice(orderDTO.getOrderPrice())
                .designImageUrl(orderDTO.getDesignImageUrl())
                .materialType(orderDTO.getMaterialType())
                .duration(orderDTO.getDuration())
                .dueDate(orderDTO.getDueDate())
                .orderStatus(orderDTO.getOrderStatus())
                .orderPrice(orderDTO.getOrderPrice())
                .build();
    }

}

