﻿using FunnregistreringsAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FunnregistreringsAPI.DAL
{
    public interface BrukerRepositoryInterface
    {
        public Task<string> CreateUser(InnBruker bruker);

        public Task<int> SendPwResetLink(String brukernavn);

        public Task<bool> ChangePassword(String brukernavn, String token, String newPassword, String newPassword2);

        public Task<string> EditUser(InnBruker bruker);

        public Task<string> DeleteUser(string brukernavn, string passord);

        public Task<Bruker> GetUser(String brukernavn);

        public Task<int> LogIn(string brukernavn, string passord);
        public Task<int> LogOut(string brukernavn);
        public Task<bool> CheckIfUserLoggedIn(string brukernavn);
    }
}
