package org.primer.kviz.model;


import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@SessionScope
public class Statistics {

    private List<Integer> solved = new ArrayList<>();
    private Integer rightAnswers;
    private int visit;

}
