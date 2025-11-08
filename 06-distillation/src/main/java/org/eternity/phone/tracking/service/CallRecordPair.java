package org.eternity.phone.tracking.service;

import org.eternity.phone.tracking.domain.CallRecord;

public record CallRecordPair(CallRecord from, CallRecord to) { }
