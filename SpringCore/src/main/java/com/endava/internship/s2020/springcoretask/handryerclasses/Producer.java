package com.endava.internship.s2020.springcoretask.handryerclasses;

import com.endava.internship.s2020.springcoretask.annotations.NameSize;
import com.endava.internship.s2020.springcoretask.annotations.NotFuture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class Producer {
    @NotNull
    @NameSize(message="Firm Name should be between 4 and 13 characters")
    private String firmName;
    @NotFuture(message = "Year should be in past!")
    private LocalDate foundationYear;
}
