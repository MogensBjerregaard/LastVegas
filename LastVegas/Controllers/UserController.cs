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

    }

}
