package fa.academy.utils;

public class Enum {

    public enum CandidateType {
        EXPERIENCE(0),
        FRESHER(1),
        INTERN(2);

        private int number;

        CandidateType(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }
    }
}
