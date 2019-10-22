using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using LastVegas.DataSources;
using LastVegas.Models;
using LastVegas.Utility;

namespace LastVegas.Repositories
{
    public class UserRepository : IUserRepository
    {
        private IUserDataSource _userDataSource;
        public UserRepository(IUserDataSource userDataSource) 
        {
            _userDataSource = userDataSource;
        }
        public async Task CreateUserAsync(User user)
        {
            await _userDataSource.InsertUserAsync(user);
        }

        public async Task<User> GetUserAsync(string emailAddress, string password)
        {
            var user = await _userDataSource.SelectUserAsync(emailAddress);
            if (user.Password.Equals(password.ToSha256Hash())) return user;
            throw new ArgumentException("User not found or incorrect password.");
        }
    }
}
