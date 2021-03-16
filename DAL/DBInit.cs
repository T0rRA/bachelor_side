﻿using FunnregistreringsAPI.Controllers;
using FunnregistreringsAPI.Models;
using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FunnregistreringsAPI.DAL
{
    public class DBInit
    {
        public static void Initialize(IApplicationBuilder app)
        {
            using (var serviceScope =
                app.ApplicationServices.CreateScope())
            {
                var context = serviceScope.ServiceProvider.GetService<FunnDB>();

                context.Database.EnsureCreated();
                context.Database.EnsureDeleted();

                // BRUKERE
                var bruker1 = new Bruker();
                bruker1.Brukernavn = "s333752@oslomet.no";
                byte[] salt = BrukerRepository.CreateSalt();
                byte[] pw = BrukerRepository.CreateHash("Fatima123$£@", salt);
                bruker1.Salt = salt;
                bruker1.Passord = pw;
                bruker1.Fornavn = "Fatima";
                bruker1.Etternavn = "Ahmad";
                bruker1.Adresse = "Osloveien 2b";
                bruker1.Postnr = "0456";
                bruker1.Poststed = "Oslo";
                bruker1.Tlf = "75849384";
                bruker1.Epost = "s333752@oslomet.no";

                context.brukere.Add(bruker1);
                context.SaveChanges();

                // FUNN
                var funn1 = new Funn();
                funn1.image = "123";
                funn1.funndato = "Kanskje bør dette være en DateTime";
                funn1.kommune = "Oslo";
                funn1.fylke = "Akershus";
                funn1.funndybde = "idk 5cm?";
                funn1.gjenstand_markert_med = "oh4o5kf";
                funn1.koordinat = "5943058390494";
                funn1.datum = "???";
                funn1.areal_type = "...sirkel";

                context.funn.Add(funn1);
                context.SaveChanges();
            }
        }
    }
}
