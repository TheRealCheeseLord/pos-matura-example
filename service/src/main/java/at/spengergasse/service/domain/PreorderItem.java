package at.spengergasse.service.domain;

public record PreorderItem(
        Long id,
        Preorder preorder,
        Product product,
        int quantity,
        Long unitPriceInCents
) {
    public Long calculateRevenueInCents() {
        return quantity * unitPriceInCents;
    }
}
