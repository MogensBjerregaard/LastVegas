using LastVegas.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LastVegas.Repositories
{
    public interface IUserRepository
    {
        Task<User> GetUserAsync(string emailAddress, string hashedPassword);
        Task CreateUserAsync(User user);
    }
}
