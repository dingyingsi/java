class Demo {
    static void main(String[] args) {
        def map = ['key1' : 'value1', 'key2' : 'value2']
        for (entry in map) {
            print entry.key + ", " + entry.value
        }
    }
}
