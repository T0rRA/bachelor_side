﻿using FunnregistreringsAPI.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FunnregistreringsAPI.DAL
{
    public class FunnDB : DbContext
    {
        public FunnDB(DbContextOptions<FunnDB> options) : base(options)
        {
            Database.EnsureCreated();
        }

        public DbSet<Bruker> brukere { get; set; }
        public DbSet<Funn> funn { get; set; }
<<<<<<< Updated upstream
=======
        public DbSet<PwReset> passordReset { get; set; }
        public DbSet<Postadresse> postadresser { get; set; }
        public DbSet<Image> Images { get; set; }
>>>>>>> Stashed changes

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseLazyLoadingProxies();
        }
    }
}
