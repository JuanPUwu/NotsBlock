import IconLogo from "../assets/img/NotsBlockIcon.png";

export default function Login() {
  return (
    <main className="bg-animated w-full h-full flex flex-col items-center justify-center">
      <form
        action=""
        className="bg-white w-90 sm:w-2/3 lg:w-[30%] lg:min-w-xl h-3/4 rounded-xl shadow-xl p-10 flex flex-col"
      >
        <div className="w-full h-1/12 mt-[9%] flex flex-row items-center justify-center gap-2">
          <img src={IconLogo} alt="NotsBlock Logo" className="h-full" />
          <h1 className="text-center text-5xl">NotsBlock</h1>
        </div>
        <h2 className="text-center text-xl text-gray-400 mt-[1%] mb-[15%]">
          Bienvenido
        </h2>
        <label htmlFor="email" className="text-gray-400 mb-[2%]">
          Email
        </label>
        <input
          type="email"
          id="email"
          placeholder="example@gmail.com"
          className="w-full h-12 border border-gray-200 rounded-xl p-3"
        />
        <label htmlFor="password" className="text-gray-400 mt-[6%] mb-[2%]">
          Contraseña
        </label>
        <input
          type="password"
          id="password"
          className="w-full h-12 border border-gray-200 rounded-xl p-3"
          placeholder=" * * * * * * * *"
        />
        <a
          href="/forgot-password"
          className="text-blue-500 hover:underline text-right mt-[4%] cursor-pointer"
        >
          ¿Has olvidado tu contraseña?
        </a>
        <button className="bg-blue-500 text-white h-12 rounded-xl mt-auto cursor-pointer">
          Ingresar
        </button>
        <span className="text-center text-gray-400 mt-[5%]">
          ¿No tienes cuenta?{" "}
          <a
            href="/register"
            className="text-blue-500 hover:underline cursor-pointer"
          >
            Regístrate
          </a>
        </span>
      </form>
      <footer className="absolute bottom-0 left-0 right-0 text-center text-white">
        Desarrollado por {""}
        <a
          href="https://github.com/JuanPUwu"
          target="_blank"
          rel="noopener noreferrer"
          className="hover:underline cursor-pointer"
        >
          @JuanPUwu
        </a>
      </footer>
    </main>
  );
}
