﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace FunnregistreringsAPI.Models
{
    //HER ER DET MYE BTW
    public class Bruker
    {
        [Required]
        [Key]
        public int UserID { get; set; }
        //Might be that username = Email
        [Required]
        public string brukernavn { get; set; }
        public byte[] passord { get; set; }
        //Salt is used to hash passwords with unique functions
        public byte[] Salt { get; set; }
        public string navn { get; set; }
        public string adresse { get; set; }
        public string postnr { get; set; }
        
        //OIDA DENNE MÅ KANSKJE HA SIN EGEN DB
        public string poststed { get; set; }
        public string tlf { get; set; }
        public string epost { get; set; }

    }
}
