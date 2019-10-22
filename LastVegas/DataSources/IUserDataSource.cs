using LastVegas.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LastVegas.DataSources
{
    public interface IUserDataSource
    {
        Task InsertUserAsync(User newUser);
        Task<User> SelectUserAsync(string email);
        Task UpdateUserAsync(User userUpdate);
        Task DeleteUserAsync(string email);
    }
}
