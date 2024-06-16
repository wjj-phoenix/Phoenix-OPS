package com.phoenix.devops.model.terminal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wjj-phoenix
 * @since 2024-06-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TerminalReceiveMsg {
    public static final Integer CMD = 1;

    public static final Integer RESIZE = 2;

    private String msg;

    private Integer type;

    private Long machineId;

    private Integer cols;

    private Integer rows;
}
