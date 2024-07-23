package douglas.Scientific.papers.controller;

import douglas.Scientific.papers.dto.ApiResponse;
import douglas.Scientific.papers.dto.OrderResponse;
import douglas.Scientific.papers.dto.PaginationResponse;
import douglas.Scientific.papers.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.BadLocationException;
import java.util.Map;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/papers/{researcherId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(@PathVariable("researcherId") Long researcherId,
                                                                 @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) throws BadLocationException {
        var pageResponse = orderService.findAllByResearcherId(researcherId, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(new ApiResponse<>(
                Map.of("response", pageResponse),
                pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)
        ));
    }
}
