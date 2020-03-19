package com.endava.internship.s2020.springcoretask;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class Producer {
    @NotNull
    @Size(min=2, max=12, message = "Name should be between 2 and 12 characters")
    private String firmName;
    @Past(message = "Year should be in past")
    private LocalDate foundationYear;

    public Producer(String firmName, LocalDate foundationYear) {
        this.firmName = firmName;
        this.foundationYear = foundationYear;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public LocalDate getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(LocalDate foundationYear) {
        this.foundationYear = foundationYear;
    }

}
