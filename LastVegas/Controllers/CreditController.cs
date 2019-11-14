using LastVegas.Models;
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


        [HttpGet("{amount}")]
        public ActionResult<int> GetAmount(int amount)
        {
            return Ok(amount);
        }

        // POST api/values
        [HttpPost]
        public ActionResult<int> Post([FromBody] CreditCard creditCard, int amount)
        {
            if (IsValid(creditCard))
            {
                return Ok(amount);
            }
            else
            {
                return BadRequest("Credit card was declined.");
            }

        }

        private bool IsValid(CreditCard card)
        {
            // validate the credit card
            return true;
        }

    }

}
