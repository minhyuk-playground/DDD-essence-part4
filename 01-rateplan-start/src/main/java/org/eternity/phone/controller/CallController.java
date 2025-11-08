package org.eternity.phone.controller;

import lombok.AllArgsConstructor;
import org.eternity.phone.service.CallDTO;
import org.eternity.phone.service.CallService;
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
