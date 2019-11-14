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
    public class CreditController : ControllerBase
    {
        public CreditController()
        {

        }

        [HttpGet]
        public ActionResult<int> Get(int amount)
        {
            return Ok(amount);
        }

        // POST api/values
        [HttpPost]
        public ActionResult<int> Post([FromBody] int amount)
        {
            if (true) //IsValid(creditCard)
            {
                return Ok(amount);
            }
            else
            {
                return BadRequest("Credit card was declined.");
            }

        }


    }

}
