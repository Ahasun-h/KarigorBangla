
package com.mdrayefenam.karigorbangla.ServiceTaker.InvoiceModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvoiceResponce {

    @SerializedName("invoice")
    @Expose
    private Invoice invoice;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

}
