using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using LastVegas.Models;
using LastVegas.Repositories;
using LastVegas.Utility;
using Microsoft.AspNetCore.Mvc;

namespace LastVegas.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private IUserRepository _userRepository;
        public UserController(IUserRepository userRepository)
        {
            _userRepository = userRepository;
        }

        // GET api/values
        [HttpGet]
        public ActionResult<int> Get()
        {
            //return new string[] { "value1", "value2" };
            return Ok(10);
        }

        // POST api/values
        [HttpPost]
        [Route("getUser")]
        public async Task<ActionResult<User>> PostGetUserAsync([FromBody] string email, string password)
        {
            try
            {
                var user = await _userRepository.GetUserAsync(email, password);
                return Ok(user);
            }
            catch (Exception e)
            {
                return NotFound(e.Message);
            }
        }

        [HttpPost]
        [Route("createUser")]
        public async Task<ActionResult<User>> PostCreateUserAsync([FromBody] string firstName, string lastName, string email, string phone, string password)
        {
            try
            {
                var user = new User()
                {
                    FirstName=firstName, LastName=lastName, Email=email, Phone=phone, Password=password
                };
                await _userRepository.CreateUserAsync(user);
                return Ok();
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }
    }

    public class Credit
    {
        public int Coins { get; set; }
    }
}
