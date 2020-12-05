class T01 {
    static void main(String[] args) {
        /*
        tellFortune({date, fortune ->
            print "${date} is ${fortune}"
        });

         */

        /*
        tellFortune() {date, fortune ->
            print "${date} is ${fortune}"
        }

         */
        tellFortune {date, fortune ->
            print "${date} is ${fortune}"
        }
    }

    static def tellFortune(closure) {
        closure new Date("09/20/2012"), "Your days is filled with ceremony"
    }
}
