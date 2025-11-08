package org.eternity.phone.tracking.controller;

import lombok.AllArgsConstructor;
import org.eternity.phone.tracking.service.CallDTO;
import org.eternity.phone.tracking.service.CallService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CallController {
    private CallService callService;

    @PostMapping("/call")
    public void call(CallDTO callDTO) {
        callService.call(callDTO);
    }
}
