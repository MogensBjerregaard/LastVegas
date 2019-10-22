using LastVegas.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LastVegas.DataSources
{
    public class UserDataSource:IUserDataSource
    {
        private IEnumerable<User> _users;
        public UserDataSource()
        {
            _users = new List<User>();
        }

        public async Task InsertUserAsync(User newUser)
        {
             await Task.Run(() => InsertUser(newUser));

        }
        private void InsertUser(User newUser)
        {
            foreach (var user in _users)
            {
                if (user.Email == newUser.Email) throw new ArgumentException("User already exists.");
            }
            _users.Append(newUser);
            
        }

        public async Task<User> SelectUserAsync(string email)
        {
            return await Task.Run(() => SelectUser(email));

        }
        private User SelectUser(string email)
        {
            foreach (var user in _users)
            {
                if (user.Email == email) return user;
            }
            throw new ArgumentException("User not found.");
        }

        public async Task UpdateUserAsync(User userUpdate)
        {
            await Task.Run(() => UpdateUser(userUpdate));

        }
        private void UpdateUser(User userUpdate)
        {
            foreach (var user in _users)
            {
                if (user.Email == userUpdate.Email)
                {
                    _users.ToList().Remove(user);
                    _users.Append(userUpdate);
                    return;
                }
            }
            throw new ArgumentException("User not found.");
        }
        public async Task DeleteUserAsync(string email)
        {
            await Task.Run(() => DeleteUser(email));

        }
        private void DeleteUser(string email)
        {
            foreach (var user in _users)
            {
                if (user.Email == email)
                {
                    _users.ToList().Remove(user);
                    return;
                }
            }
            throw new ArgumentException("User not found.");
        }
    }
}
