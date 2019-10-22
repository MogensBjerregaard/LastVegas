using LastVegas.Utility;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Security;
using System.Threading.Tasks;

namespace LastVegas.Models
{
    public class User
    {
        public string FirstName { get; set; }

        public string LastName { get; set; }

        [Required(ErrorMessage = "Email Adress is required")]
        [EmailAddress(ErrorMessage = "Invalid Email Address")]
        public string Email { get; set; }

        [Phone(ErrorMessage = "Invalid Phone Number")]
        public string Phone { get; set; }

        private string _hashedPassword;

        public string Password { get { return _hashedPassword; } set { _hashedPassword = value.ToSha256Hash(); } }

        public int Credit { get; set; }
    }
}
