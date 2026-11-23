class ProductOfNumbers {
    List<Integer> a = new ArrayList<>();

    public ProductOfNumbers() {}

    public void add(int num) {
        a.add(num);
    }

    public int getProduct(int k) {
        int n = a.size(), prod = 1;
        for (int i = n - k; i < n; i++)
            prod *= a.get(i);
        return prod;
    }
}