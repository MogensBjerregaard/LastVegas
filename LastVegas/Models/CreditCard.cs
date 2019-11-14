using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LastVegas.Models
{
    public class CreditCard
    {
        public int Number { get; set; }
        public int ExpirationMonth { get; set; }
        public int ExpirationYear { get; set; }
        public int Cvc { get; set; }
        public CreditCard(int number, int expirationMonth, int expirationYear, int cvc)
        {
            Number = number;
            ExpirationMonth = expirationMonth;
            ExpirationYear = expirationYear;
            Cvc = cvc;
        }
    }
}
